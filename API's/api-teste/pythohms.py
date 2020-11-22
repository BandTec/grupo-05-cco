import requests
import json

class CrawlerOpenHardwareMonitor:
    def __init__(self):
        self.url = 'http://localhost:8085/data.json' # NÃO ESQUECE DE ARRUMAR O IP E A PORTA!
        self.data = None
    
    def getJsonData(self):
        response = requests.get(self.url)
        data = response.json()
        self.data = data
    
    def getInfo(self):
        self.getJsonData()
        info = {
            "Desktop": None,
            "MotherBoard": None,
            "CPU": [],
            "Memory": {
                "Load": None,
                "Use": None,
                'Available': None
            },
            "VideoCard": None,
            "AllDevices": [],
            "Disk": None
        }

        clocks = []
        temperatures = []
        loads = []
        
        data = self.data

        for i in data['Children']:
            info['Desktop'] = i['Text']
            for desktop in i['Children']:
                if desktop['id'] <= 2:
                    info['MotherBoard'] = desktop['Text']
                if desktop['Text'].find('Generic Hard Disk') < 0:
                    info['AllDevices'].append(desktop['Text'])
                #CPU
                if desktop['Text'].find('Intel') >= 0 or desktop['Text'].find('AMD') >= 0:
                    for cpu_metrics in desktop['Children']:
                        #clock
                        if cpu_metrics['Text'] == 'Clocks':
                            for clock in cpu_metrics['Children']:
                                if clock['Text'].find('CPU') >= 0:
                                    clocks.append(clock['Value'])
                        #temperature
                        if cpu_metrics['Text'] == 'Temperatures':
                            for temps in cpu_metrics['Children']:
                                if temps['Text'].find('CPU') >= 0:
                                    temperatures.append(temps['Value'])
                        #load
                        if cpu_metrics['Text'] == 'Load':
                            for load in cpu_metrics['Children']:
                                if load['Text'].find('CPU') >= 0:
                                    loads.append(load['Value'])
                #Memory
                if desktop['Text'].find('Generic Memory') >= 0 or desktop['Text'].find('Memory') >= 0:
                    for cpu_metrics in desktop['Children']:
                        #Load
                        if cpu_metrics['Text'] == 'Load':
                            for memory in cpu_metrics['Children']:
                                if memory['Text'] == 'Memory':
                                    info['Memory']['Load'] = memory['Value']
                        #Use
                        if cpu_metrics['Text'] == 'Data':
                            for memory in cpu_metrics['Children']:
                                if memory['Text'] == 'Used Memory':
                                    info['Memory']['Use'] = memory['Value']
                                if memory['Text'] == 'Available Memory':
                                    info['Memory']['Available'] = memory['Value']
                #Disk
                if desktop['Text'].find(info['AllDevices'][-1]) >= 0:
                    for hd in desktop['Children']:
                        if hd['Text'].find('Load') >=0:
                            for load in hd['Children']:
                                info['Disk'] = load['Value']

            for index in range(len(clocks)):
                cpu = {
                    'Name': f'Core {index + 1}',
                    "Clock": clocks[index],
                    "Temperature": temperatures[index],
                    "Load": loads[index]
                }
                info['CPU'].append(cpu)
            #print(info)
            return info
    
    def getData(self):
        info = self.getInfo()
        data = {
            'CPU': None,
            'RAM': None,
            'Disco': None,
            'Temperatura': None,
            'Frequencia': None
        }
        cores = info['CPU']
        nCores = len(cores)
        somaProcessamento = 0.0
        somaLoadProcessamento = 0.0
        somaTemp = 0.0
        for i in range(nCores):
            core = self.getNumber(cores[i]['Clock'])
            somaProcessamento += float(core)
            somaLoadProcessamento += float(self.getNumber(cores[i]['Load']))
            somaTemp += float(self.getNumber(cores[i]['Temperature']))
        data['Frequencia'] = somaProcessamento/nCores
        data['CPU'] = somaLoadProcessamento/nCores
        data['Temperatura'] = somaTemp/nCores
        data['RAM'] = self.getNumber(info['Memory']['Load'])
        data['Disco'] = self.getNumber(info['Disk'])

        print("this is data in getData():",data)
        return data
    
    def getNumber(self, value):
        aux = value.split(' ')[0]
        return aux.replace(',','.')

    def getValor(self, componente):
        data = self.getData()
        print("This data from the getValor",data)
        if componente.find('CPU') >= 0:
            return data['CPU']
        elif componente.find('RAM') >= 0:
            return data['RAM']
        elif componente.find('Temperatura') >= 0:
            return data['Temperatura']
        elif componente.find('Disco') >=0:
            return data['Disco']
        elif componente.find('Frequencia') >=0:
            return data['Frequencia']
import requests
import json
import pdb

class CrawlerOpenHardwareMonitor:
    def __init__(self):
        self.url = 'http://localhost:8085/data.json'
        self.data = None
    
    def getJsonData(self):
        response = requests.get(self.url)
        data = json_data = response.json()
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
            "AllDevices": []
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

            for index, itens in enumerate(clocks):
                if index >= len(temperatures):
                    temp = "---"
                else:
                    temp = temperatures[index]
                cpu = {
                    'Name': f'Core {index + 1}',
                    "Clock": clocks[index],
                    "Temperature": temp,
                    "Load": loads[index]
                }
                info['CPU'].append(cpu)
            # return info

        user_desktop = info["Desktop"]
        placa_mae = info["MotherBoard"]
        cpu_count = len(info["CPU"]) 
        clock_1 = float(info["CPU"][0]["Clock"].replace(",",".").replace("MHz","").strip())
        clock_2 = float(info["CPU"][1]["Clock"].replace(",",".").replace("MHz","").strip())
        memory_load = info["Memory"]["Load"]
        memory_use = info["Memory"]["Use"]
        memory_available = info["Memory"]["Available"]
        video_card = info["VideoCard"]
        soma_temperature = 0.0
        soma_percent = 0.0
        soma_clock = 0.0

        for i in info["CPU"]:
            cpu_name = i["Name"]
            cpu_clock = float(i["Clock"].replace(",",".").replace("MHz","").strip())
            cpu_temperature = float(i["Temperature"].replace(",",".").replace("°C","").strip())
            cpu_load = float(i["Load"].replace("%","").replace(",",".").strip())
            soma_temperature += cpu_temperature
            cpu_media_temperatura = soma_temperature / cpu_count
            soma_percent += cpu_load
            cpu_media_percent = soma_percent / cpu_count
            soma_clock += cpu_clock
            cpu_media_clock = soma_clock / cpu_count

        data = (user_desktop, placa_mae, cpu_count, cpu_media_temperatura, round(cpu_media_percent,2), round(cpu_media_clock,2), memory_load.replace("%","").replace(",",".").strip(), memory_use.replace("GB","").replace(",",".").strip(), memory_available.replace("GB","").replace(",",".").strip(), video_card)

        return data

    def getData(self):
        info = self.getInfo()
        data = {
            'Usuario': None,
            'Placa Mãe': None,
            'Quantidade CPU': None,
            'CPU': None,
            'RAM': None,
            'Disco': None,
            'Temperatura': None,
            'Frequencia': None,
            'Memória Usada'
            'Memória disponível': None,
            'Placa de Video': None
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
        # data['Placa Mãe'] != ''
        data['Frequencia'] = somaProcessamento/nCores
        data['CPU'] = somaLoadProcessamento/nCores
        data['Temperatura'] = somaTemp/nCores
        data['RAM'] = self.getNumber(info['Memory']['Load'])
        data['Disco'] = self.getNumber(info['Disk'])

        print("data no pythohms-getData():",data)
        return data

if __name__ == "__main__":
    teste =  CrawlerOpenHardwareMonitor()
    teste.getInfo()

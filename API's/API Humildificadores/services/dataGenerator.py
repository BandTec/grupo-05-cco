import psutil
import requests

def getData():
    cpu_info = {
    'cpu': 0,
    'cpu_count': 0,
    'memory': 0,
    'memory_percent': 0,
    'disk': 0,
    'user_name': 0
    }
    cpu = psutil.cpu_percent(interval=1, percpu=True)
    cpu_media = sum(cpu)/len(cpu)
    cpu_count =  psutil.cpu_count()
    memory = (psutil.virtual_memory().used >> 30)
    memory_percent = (psutil.virtual_memory().percent)
    disk = psutil.disk_usage('/').percent
    user_name = psutil.Process().as_dict(attrs=['username'])["username"]

    cpu_info['cpu'] = round(cpu_media)
    cpu_info['cpu_count'] = cpu_count
    cpu_info['memory'] = memory
    cpu_info['memory_percent'] = memory_percent
    cpu_info['disk'] = disk
    cpu_info['user_name'] = user_name

    #Objeto para visualização só
    print(cpu_info)
    #lista para envio no banco
    data = (round(cpu_media), cpu_count, memory, memory_percent, disk, user_name)

    print(data)
        
    return data

def alertaSlack(valores):
    url = 'https://hooks.slack.com/services/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'
    if (valores[0] >= 30) or (valores[3] >= 60) or (valores[4] >= 60):
        alerta = ''
        if(valores[0] >= 80):
            alerta += "Atenção maquina {maquina}! CPU com {number:.1f}% de uso. \n".format(maquina = valores[5], number = valores[0])
        if(valores[3] >= 50):
            alerta += "Atenção maquina {maquina}! Memoria com {number:.1f}% de uso. \n".format(maquina = valores[5], number = valores[3])
        if(valores[4] >= 80):
            alerta += "Atenção maquina {maquina}! Disco com {number:.1f}% de uso. \n".format(maquina = valores[5], number = valores[4])



        pload = {'text': alerta}
        requests.post(url, json = pload)
        return alerta
    else:
        return "tranquilinho"




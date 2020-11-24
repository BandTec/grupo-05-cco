from pythohms import CrawlerOpenHardwareMonitor
from conexaobd import Mysql
from maquinas import Maquina
import time

mysql = Mysql('Kaio', 'bandtec', '127.0.0.1', 'humildificadores')

mysql.connect()

maquina = Maquina(1,'kaiob', 1)
configuracoes = maquina.getConfiguracoes(mysql)
print("Entrando no while")
while True:
    cont = 1
    print("TravouAqui: cont = 1")
    for config in configuracoes:
        print('Gerando {}º Dado: '.format(cont))
        dado = config.gerarDado(mysql)
        dado.inseriRegistro(mysql)
        print('\n')
        cont+=1
    time.sleep(2)
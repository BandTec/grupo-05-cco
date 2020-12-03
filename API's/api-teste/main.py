from pythohms import CrawlerOpenHardwareMonitor
from conexaobd import Mysql
from maquinas import Maquina
from automatizacao import Automatizando
import time


mysql = Mysql('root', 'bandtec', 'localhost', 'humildificadores')
automatizacao = Automatizando()

componente = automatizacao.AutomatizandoScript()
comp = mysql.insertComponentes(componente)




# maquina = Maquina(1,'kaiob', 1)
# print(crawler.info['Desktop'])
# x =  mysql.select('select * from maquinas where idMaquina= {}'.format(z))
# while True:
#     cont = 1
#     print("TravouAqui: cont = 1")
#     for config in configuracoes:
#         print('Gerando {}º Dado: '.format(cont))
#         dado = config.gerarDado(mysql)
#         dado.inseriRegistro(mysql)
#         print('\n')
#         cont+=1
#     time.sleep(2)
import time
from conexaobd import Mysql
from pythohms import CrawlerOpenHardwareMonitor

mysql = Mysql('Kaio', 'bandtec', '127.0.0.1', 'humildificadores')
crawler = CrawlerOpenHardwareMonitor()
mysql.connect()
crawler.getInfo()

class Automatizando:
    def AutomatizandoScript(self):
        print("Olá, seja bem vindo...")
        time.sleep(1)
        try:
            mysql.selectMaquinaUsuario(crawler.getInfo())
            mysql.selectComponentes()
            x = str(input("Quais componentes você deseja monitorar?\nDigite q para sair\n"))
            x.lower()
            lista_componentes = [] 
            while True: 
                z = str(input())
                if z == "q":
                    break
                lista_componentes.append(z) 
                print("Adicionando itens...")
                print(lista_componentes)
        except Exception as err:
            print(err)

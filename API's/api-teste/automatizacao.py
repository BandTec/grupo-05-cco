import time
from conexaobd import Mysql
from pythohms import CrawlerOpenHardwareMonitor

mysql = Mysql('root', 'bandtec', 'localhost', 'humildificadores')
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
            lista_componentes = [x] 
            while True: 
                z = str(input())
                if z == "q":
                    break
                lista_componentes.append(z)
                time.sleep(1) 
                print("\nAdicionando itens...")
                print(lista_componentes)
        except Exception as err:
            print(err)

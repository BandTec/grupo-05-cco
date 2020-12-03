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
            x = str(input("Quais componentes você deseja monitorar?\n"))
            x.lower()
            lista_componentes = [x]
            while True: 
                print("Digite [Q] para sair\n")
                z = str(input("Digite aqui: "))
                if z.lower() == "q" or x.lower() == "q":
                    break
                lista_componentes.append(z)
            time.sleep(1) 
            print("\nAdicionando itens...")
            print(lista_componentes)
        except Exception as err:
            print(err)

        return lista_componentes        

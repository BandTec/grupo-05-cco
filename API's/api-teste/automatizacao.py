import time
from conexaobd import Mysql
from pythohms import CrawlerOpenHardwareMonitor
import time

mysql = Mysql('root', 'bandtec', 'localhost', 'humildificadores')
crawler = CrawlerOpenHardwareMonitor()
mysql.connect()
crawler.getInfo()

class Automatizando:
    
    def AutomatizandoScript(self):
        print("Olá, seja bem vindo...")
        time.sleep(1)
        try:
            # mysql.selectMaquinaUsuario(crawler.getInfo())
            while True:
                mysql.inserindoValores(crawler.getInfo())
                time.sleep(2.0)
        except Exception as err:
            print(err)


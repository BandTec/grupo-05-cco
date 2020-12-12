from conexaobd import Mysql
from pythohms import CrawlerOpenHardwareMonitor
import time

mysql = Mysql('root', 'urubu100', '54.209.135.215', 'humildificadores')
crawler = CrawlerOpenHardwareMonitor()
mysql.connect()
crawler.getInfo()

class Automatizando:
    
    def AutomatizandoScript(self):
        print("Olá, seja bem vindo...")
        time.sleep(1)
        try:
            mysql.selectMaquinaUsuario(crawler.getInfo()[0])
            while True:
                mysql.inserindoValores(crawler.getInfo(), crawler.getInfo()[0])
                time.sleep(5)
        except Exception as err:
            print(err)


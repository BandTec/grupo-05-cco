from services.mysql import Mysql
from services.pythohms import CrawlerOpenHardwareMonitor
import time

#Inserir user, password, host, database
mysql = Mysql('ProjetoAPI','urubu100', 'localhost', 'PROJETOAPI')

mysql.connect()

while True:
    teste =  CrawlerOpenHardwareMonitor()
    values = teste.getInfo()
    print(values)
    mysql.select_usuarios()
    mysql.insert(values)
    time.sleep(3)

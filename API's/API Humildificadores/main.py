from services.mysql import Mysql
from services.dataGenerator import getData
from services.dataGenerator import getUser
from services.dataGenerator import alertaSlack
import time

#Inserir user, password, host, database
mysql = Mysql('root', 'bandtec', 'localhost', 'humildificadores')

mysql.connect()

usuario = getUser();

print("\nSeja bem vindo",usuario,"!")

selectUsuario = mysql.select_usuarios()

if selectUsuario:
    print("\nTá dentro pô")
else:
    print("\nTá não mano")
    parque = int(input("\nQual parque você está? \n1-Ibirapuera \n2-Chico Mendes"))
    insertUsuario = mysql.insert_usuarios()

# print(selectUsuario)

# while True:
#     select = mysql.select()
#     print(select)
#     values = getData()
#     # mysql.insert(values)
#     Slack = alertaSlack(values)
#     print(Slack)
#     time.sleep(2)

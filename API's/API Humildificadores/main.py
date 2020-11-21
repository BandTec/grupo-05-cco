from services.mysql import Mysql
from services.dataGenerator import getData
from services.dataGenerator import getUser
from services.dataGenerator import alertaSlack
import time

#Inserir user, password, host, database
mysql = Mysql('Kaio', 'bandtec', '127.0.0.1', 'humildificadores')

mysql.connect()

# Resgatando usuário
usuario = getUser()
# query=('select usuario from maquinas where usuario = ',(usuario))
userSelect = mysql.select_usuarios(usuario)



print("\nSeja bem vindo",userSelect,"!")

# x=7
# if x==0:
#     print("\nVocê está cadastrado")
# else:
#     print("\nVi que você é um novo usuário, por favor, informe as informações abaixo")
#     parqueUsuario = int(input("\nQual parque você está trabalhando? \n1-Ibirapuera \n2-Chico Mendes \n"))

#     # Lista de cadastro de usuário
#     user_info = (usuario,parqueUsuario)

#     values = user_info

#     mysql.insert_usuarios(values)

#     print("Pronto irmão")


# while True:
#     select = mysql.select()
#     print(select)
#     values = getData()
#     # mysql.insert(values)
#     Slack = alertaSlack(values)
#     print(Slack)
#     time.sleep(2)

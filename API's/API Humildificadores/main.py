from services.mysql import Mysql
from services.dataGenerator import getData
from services.dataGenerator import getUser
from services.dataGenerator import alertaSlack
import time

#Inserir user, password, host, database
mysql = Mysql('root', 'bandtec', 'localhost', 'humildificadores')

mysql.connect()

# Resgatando usuário
usuario = getUser()
userSelect = mysql.select_usuarios(usuario)

# print("\nuserSelect esse: ",userSelect)

print("\nSeja bem vindo",userSelect,"!")

# print("Esse é o user_select:",userSelect)

# if userSelect:
#     print("\nVocê está cadastrado")
# else:
#     print("\nVi que você é um novo usuário, por favor, informe as informações abaixo")
    # parqueUsuario = int(input("\nQual parque você está trabalhando? \n1-Ibirapuera \n2-Chico Mendes \n"))

    # localParque = str(input("Informe a área do parque que você está: (ex: Área-7b) \n"))

    # # Lista de cadastro de usuário
    # user_info = (usuario,localParque,parqueUsuario)

    # values = user_info

    # mysql.insert_usuarios(values)

# print(selectUsuario)

# while True:
#     select = mysql.select()
#     print(select)
#     values = getData()
#     # mysql.insert(values)
#     Slack = alertaSlack(values)
#     print(Slack)
#     time.sleep(2)

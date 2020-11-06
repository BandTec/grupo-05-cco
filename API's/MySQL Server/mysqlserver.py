import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="bandtec",
  database="projetoapi"
)

# print(mydb)
global opcao_desejada
global a

while True:
    opcao_desejada = input("Você deseja monitorar algo? [S/N]:\n")

    if opcao_desejada.upper() == 'N':
        break

    meucursor = mydb.cursor()
    select = meucursor.execute("SELECT column_name FROM information_schema.columns WHERE  table_name = 'pytohms' AND table_schema = 'projetoapi'")
    meuresultado = meucursor.fetchall()
    for x in meuresultado:
        print(x)
    
    a = input("O que você deseja monitorar?:\n")

    b = input("Quantos itens você deseja trazer?:\n")

    mycursor = mydb.cursor()

    mycursor.execute("select {} from pytohms limit {}".format(a,b))

    myresult = mycursor.fetchall()

    contador = 1
    for y in myresult:
        print(contador,"-", y[0])
        contador+= 1



    
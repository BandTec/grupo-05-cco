import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="bandtec",
  database="projetoapi"
)

mycursor = mydb.cursor()

mycursor.execute("CREATE TABLE customers (name VARCHAR(255), address VARCHAR(255))")

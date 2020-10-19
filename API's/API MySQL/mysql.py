import pymysql

# Open database connection
banco = pymysql.connect("root","bandtec","localhost","projetoapi")

# prepare a cursor object using cursor() method
cursor = banco.cursor()

# execute SQL query using execute() method.
cursor.execute("select * from pytohms")

# Fetch a single row using fetchone() method.
data = cursor.fetchone()
print ("Database version : %s " % data)

# disconnect from server
banco.close()
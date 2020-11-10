import pyodbc

server = 'serverhumildificadores.database.windows.net' 
database = 'bancohumildificadores' 
username = 'adminlocal' 
password = 'Safadinhos123@' 
driver = 'ODBC Driver 17 for SQL Server'

with pyodbc.connect('DRIVER='+driver+';SERVER='+server+';PORT=1433;DATABASE='+database+';UID='+username+';PWD='+ password) as conn:
    with conn.cursor() as cursor:
        cursor.execute("select * from cliente")
        row = cursor.fetchone()
        while row:
            # print ("idParque" + " " + str(row[0]) + " " + "Nome" + " " + str(row[1]) + " " + "CPF" + " " + str(row[2]) + " " + "Area" + " " + str(row[3]))
            # print("\n")
            print(row)
            row = cursor.fetchone()
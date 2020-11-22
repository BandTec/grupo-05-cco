import mysql.connector

class Mysql:
    def __init__(self, user, password, host, database):
        self.user = user
        self.password = password
        self.host = host
        self.database = database
        self.mysql = None
        self.cursor = None

    #Estabelecendo uma conexão
    def connect(self):
        try:
            self.mysql = mysql.connector.connect(
            user=self.user, password=self.password, host=self.host, database=self.database)
            #Criando cursor para manipulação do banco.
            self.cursor = self.mysql.cursor()
        except Exception as err:
            print(err)
            raise
    #Insert no banco
    def insert(self, query, values):
        try:
            print('\nInserindo Valores')
            self.cursor.execute(query,values)
            self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()
    
    #Select no banco
    def select(self, query):
        try:
            print('\nRealizando Select')
            self.cursor.execute(query)
            result = self.cursor.fetchall()
            self.mysql.commit()
            print("select feito:",result)
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()
            result = None
        
        return result
        
    # Fechando conexão
    def close(self):
        self.mysql.close()
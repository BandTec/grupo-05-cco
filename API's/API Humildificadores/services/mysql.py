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
            user=self.user, password=self.password, host=self.host, database=self.database, auth_plugin='mysql_native_password')
            #Criando cursor para manipulação do banco.
            print(self.mysql)
            self.cursor = self.mysql.cursor()
        except Exception as err:
            print(err)
            raise

    # select's usados na API

    def select_usuarios(self,usuario):
        query = "SELECT usuario FROM maquinas"
        try:
            print('\nVerificando cadastro')
            self.cursor.execute(query)
            meuresultado = self.cursor.fetchall()
            for x in meuresultado:
                # print("Procurando você...")
                if x[0]==usuario:
                    # print("Aqui está:",x[0])
                    meuresultado=x[0]
                    break
                # print("olha: {}".format(x[0]))
            # print("\nEsse é o meuresultado:",meuresultado)
            return meuresultado
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()

    # Insert's usados na API

    def insert_usuarios(self, user_info):
        query = (
            "INSERT INTO maquinas (usuario,localizacaoMaquina,fkParque)"
            "VALUES (%s, %s, %s)"
        )
        values = user_info
        try:
            print('Inserindo Novo Usuário')
            self.cursor.execute(query,values)
            self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()
    
    # Fechando conexão
    def close(self):
        self.mysql.close()

        



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


    # select usuario
    def select_usuarios(self, usuario_maquina):
        try:
            print('Usuários não cadastrados')
            select_userName = "select * from maquinas where usuario = %s"
            self.cursor.execute(select_userName, usuario_maquina)
                # "select * from maquinas"
                # "where usuario = %s" % (user_name)
            meuresultado = self.cursor.fetchall()
            for x in meuresultado:
                print(x)
            # self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()

    # Inserção na tabela
    def insert(self, data):
        query = (
            "INSERT INTO pytohms (placa_mae, cpu_count, cpu_media_temperatura, cpu_media_percent, cpu_media_clock, memory_load, memory_use, memory_available, video_card)"
            "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
        )
        values = data
        try:
            print('Inserindo Valores')
            self.cursor.execute(query,values)
            self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()
    
    # Fechando conexão
    def close(self):
        self.mysql.close()
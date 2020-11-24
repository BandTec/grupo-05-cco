import mysql.connector
import time

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
    
    def selectMaquinaUsuario(self, usuario_maquina):
        try:
            print('\nRealizando busca de sua máquina')
            time.sleep(1)
            select_maquina = "select * from maquinas where usuario ='{}';".format(usuario_maquina)
            self.cursor.execute(select_maquina)
            meuresultado = self.cursor.fetchall()

            for x in meuresultado:
                print(x)
            
            if len(meuresultado) >= 1:
                print("Busca bem sucedida. Máquina encontrada")
            else:
                print("Busca mal sucedida, não encontramos sua máquina. Vamos criar uma para você")
                try:
                    print('Criando máquina...\n')
                    insert_maquina = "insert into maquinas values (null,'{}','{}')".format(usuario_maquina, 1)
                    self.cursor.execute(insert_maquina)
                    self.mysql.commit()
                    print("Maquina criada com sucesso")
                except Exception as err:
                    print(err)
                    self.mysql.rollback()
                    self.mysql.close()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.mysql.close()

    def selectComponentes(self):
        try:
            print('Mostrando componentes para escolha')
            time.sleep(1)
            select_maquina = "select nome from componentes"
            self.cursor.execute(select_maquina)
            meuresultado = self.cursor.fetchall()
            for x in meuresultado:
                print('[{}]'.format(x[0]))
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.mysql.close()

    # Fechando conexão
    def close(self):
        self.mysql.close()
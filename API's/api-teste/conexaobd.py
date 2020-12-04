import mysql.connector
import time
from datetime import datetime

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
            select_maquina = "select fkMaquina,usuario from maquinas m, configuracao c where  c.fkMaquina = m.idMaquina and usuario = '{}';".format(usuario_maquina)
            self.cursor.execute(select_maquina)
            meuresultado = self.cursor.fetchall()

            # for row in meuresultado:
            #     print(row[0])
            
            if len(meuresultado) >= 1:
                print("Busca bem sucedida. Máquina encontrada. Seja bem vindo!")                
            else:
                print("Busca mal sucedida, não encontramos sua máquina. Vamos criar uma para você")
                try:
                    print('Criando máquina...\n')
                    insert_maquina = "insert into maquinas values (null,'{}','{}')".format(usuario_maquina, 1)
                    self.cursor.execute(insert_maquina)
                    self.mysql.commit()
                    try:
                        print('Mostrando componentes para escolha')
                        time.sleep(1)
                        select_maquina = "select nome from componentes"
                        self.cursor.execute(select_maquina)
                        meuresultado = self.cursor.fetchall()
                        contador = 0
                        for x in meuresultado:
                            contador+=1
                            print(contador,'-','[{}]'.format(x[0]))
                        print("Maquina criada com sucesso\n")
                        x = str(input("Quais componentes você deseja monitorar?\n"))
                        x.lower()
                        lista_componentes = [x]
                        while True: 
                            print("Digite [Q] para sair\n")
                            z = str(input("Digite aqui: "))
                            if z.lower() == "q" or x.lower() == "q":
                                break
                            lista_componentes.append(z)
                        time.sleep(1) 
                        print("\nAdicionando itens: ",end=' ')
                        for row in lista_componentes:
                            print(row, end=',')
                        print(lista_componentes)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()
                except Exception as err:
                    print(err)
                    self.mysql.rollback()
                    self.mysql.close()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.mysql.close()
        return usuario_maquina      



    def inserindoValores(self, valor, usuario_maquina):
        try:
            select = "select nome from configuracao ,maquinas, componentes where idComponente = fkComponente and idMaquina = fkMaquina;"
            self.cursor.execute(select)
            result = self.cursor.fetchall()
            self.mysql.commit()
            now = datetime.now()
            data_formatada = now.strftime('%Y-%m-%d %H:%M:%S')
            for row in result:
                print(row[0])
                if row[0] == 'cpu_media_temperatura':
                    try:
                        print("Inserindo Media de Tempertura da CPU")
                        insertando = "insert into leituras values (null, '{}','{}', 1)".format(valor[3], data_formatada)
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()
                if row[0] == 'memory_use':
                    try:
                        print("Inserindo Uso de Memoria")
                        insertando = "insert into leituras values (null, '{}','{}', 3)".format(valor[7], data_formatada)
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()
                if row[0] == 'cpu_media_percent':
                    try:
                        print("Inserindo Media de Percentual da CPU")
                        insertando = "insert into leituras values (null, '{}','{}', 2)".format(valor[4], data_formatada)
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()
            
            print(insertando)
            print("Valor inserido com sucesso")
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.mysql.close()


    # Fechando conexão
    def close(self):
        self.mysql.close()
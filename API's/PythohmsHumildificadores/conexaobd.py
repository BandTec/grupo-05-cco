import mysql.connector
import time
from datetime import datetime


idMaquina = []
idConfiguracao = 0

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
  
    def selectMaquinaUsuario(self, usuario_maquina):
        try:
            print('\nRealizando busca de sua máquina\n')
            time.sleep(1)
            select_maquina = "select fkMaquina,usuario from maquinas m, configuracao c where  c.fkMaquina = m.idMaquina and usuario = '{}';".format(usuario_maquina)
            self.cursor.execute(select_maquina)
            meuresultado = self.cursor.fetchall()
            
            if len(meuresultado) >= 1:
                print("Busca bem sucedida. Máquina encontrada. Seja bem vindo!\n")             
            else:
                print("Busca mal sucedida, não encontramos sua máquina. Vamos criar uma para você\n")
                try:
                    print('Criando máquina...\n')
                    try:
                        parques = "select nome from parque;"
                        self.cursor.execute(parques)
                        select_parques = self.cursor.fetchall()
                        contador = 1
                        for x in select_parques:
                            print("{} - {}".format(contador, x[0]))
                            contador += 1
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                    parque = int(input("Digite o número o parque: "))
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

                        insert_maquina = "insert into maquinas values (null, '{}', {})".format(usuario_maquina, parque)
                        self.cursor.execute(insert_maquina)
                        self.mysql.commit()
                        try:
                            select_fkMaquina = "select idMaquina from maquinas where usuario = '{}';".format(usuario_maquina)
                            self.cursor.execute(select_fkMaquina)
                            meuresultado = self.cursor.fetchall()
                            for x in meuresultado:
                                idMaquina = x[0]
                        except Exception as err:
                            print(err)
                            self.mysql.rollback()
                            self.mysql.close()
                        for row in lista_componentes:
                            print(lista_componentes)
                            print(row)
                            print('Inserindo...\n')
                            if row == 'cpu_count':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 1, null)".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
                                except Exception as err:
                                    print(err)
                                    self.mysql.rollback()
                                    self.mysql.close()

                            if row == 'cpu_media_temperatura':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 2, '80.00')".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
                                except Exception as err:
                                    print(err)
                                    self.mysql.rollback()
                                    self.mysql.close()

                            if row == 'cpu_media_percent':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 3,'90.00')".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
                                except Exception as err:
                                    print(err)
                                    self.mysql.rollback()
                                    self.mysql.close()

                            if row == 'cpu_media_clock':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 4, null)".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
                                except Exception as err:
                                    print(err)
                                    self.mysql.rollback()
                                    self.mysql.close()

                            if row == 'memory_load':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 5, null)".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
                                except Exception as err:
                                    print(err)
                                    self.mysql.rollback()
                                    self.mysql.close()

                            if row == 'memory_use':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 6,'90.00')".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
                                except Exception as err:
                                    print(err)
                                    self.mysql.rollback()
                                    self.mysql.close()
                                    
                            if row == 'memory_available':
                                try:
                                    insert_config = "insert into configuracao values (null, {}, 7, null)".format(idMaquina)
                                    print(insert_config)
                                    self.cursor.execute(insert_config)
                                    self.mysql.commit()
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
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.mysql.close()
        return usuario_maquina      



    def inserindoValores(self, valor, usuario_maquina):
        try:
            select = "select nome from configuracao, maquinas, componentes where idComponente = fkComponente and idMaquina = fkMaquina and usuario = '{}';".format(usuario_maquina)
            self.cursor.execute(select)
            result = self.cursor.fetchall()
            self.mysql.commit()
            try:
                select_fkMaquina = "SELECT idConfiguracao, fkComponente  FROM configuracao con, maquinas m2 where m2.idMaquina = con.fkMaquina and m2.usuario = '{}';".format(usuario_maquina)
                
                self.cursor.execute(select_fkMaquina)
                meuresultado = self.cursor.fetchall()
                for x in meuresultado:
                    idMaquina.append([x[0], x[1]])
            except Exception as err:
                print(err)
                self.mysql.rollback()
                self.mysql.close()
            print(idMaquina)
            now = datetime.now()
            data_formatada = now.strftime('%Y-%m-%d %H:%M:%S')



            for row in idMaquina:
                if row[1] == 1:
                    try:
                        print("\nInserindo Contagem da CPU")
                        insertando = "insert into leituras values (null, {},'{}', {}, 1)".format(valor[1], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                if row[1] == 2:
                    try:
                        print("\nInserindo Media de Temperatura da CPU")
                        insertando = "insert into leituras values (null, {},'{}', {}, 2)".format(valor[2], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                if row[1] == 3:
                    try:
                        print("\nInserindo Media de Percentual da CPU")
                        insertando = "insert into leituras values (null, {},'{}', {}, 3)".format(valor[3], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                if row[1] == 4:
                    try:
                        print("\nInserindo Media de Clock da CPU")
                        insertando = "insert into leituras values (null, {},'{}', {}, 4)".format(valor[4], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                if row[1] == 5:
                    try:
                        print("\nInserindo Percentual de uso da Memória")
                        insertando = "insert into leituras values (null, {},'{}', {}, 5)".format(valor[5], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                if row[1] == 6:
                    try:
                        print("\nInserindo Uso de Memoria")
                        insertando = "insert into leituras values (null, {},'{}', {}, 6)".format(valor[6], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()

                if row[1] == 7:
                    try:
                        print("\nInserindo Quantidade de Memória Disponível")
                        insertando = "insert into leituras values (null, {},'{}', {}, 7)".format(valor[7], data_formatada, row[0])
                        self.cursor.execute(insertando)
                        self.mysql.commit()
                        print(insertando)
                    except Exception as err:
                        print(err)
                        self.mysql.rollback()
                        self.mysql.close()
                
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.mysql.close()


    # Fechando conexão
    def close(self):
        self.mysql.close()
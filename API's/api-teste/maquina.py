from configuracao import Configuracao
class Maquina:
    def __init__(self, idMaquina, usuario, fkParque):
        self.idMaquina = idMaquina
        self.usuario = usuario
        self.fkParque = fkParque
    
    def getConfiguracoes(self, mysql):
        query = "SELECT * FROM configuracao WHERE fkMaquina = {}".format(self.idMaquina)
        print("\ngetConfiguracoes em maquina.py")
        print("query:",query)
        result = mysql.select(query)
        print("\ngetConfiguracoes em maquina.py - result:",result)
        configuracoes = []
        for i in result:

            configuracoes.append(Configuracao(
                i[0], i[1], i[2], i[3]
            ))
        print("\nreturn do configuracoes:",configuracoes)
        return configuracoes
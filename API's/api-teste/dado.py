class Dado:
    def __init__(self, idMetrica, valor, momento, fkConfiguracao):
        self.idMetrica = idMetrica
        self.valor = valor
        self.momento = momento
        self.fkConfiguracao = fkConfiguracao
    
    def inseriRegistro(self, mysql):
        query = ( "INSERT INTO leituras VALUES "
            "(null, %s, %s, %s)" )
        print("inseriRegistro")

        values = (self.valor, self.fkConfiguracao,
            self.momento)
        mysql.insert(query, values)
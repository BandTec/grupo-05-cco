import datetime
from bdmysql import Mysql
from pythohms import CrawlerOpenHardwareMonitor
from dado import Dado

class Configuracao:
    def __init__(self, idConfiguracao, fkMaquina, fkComponente, limiteAlerta):
        self.idConfiguracao = idConfiguracao
        self.fkMaquina = fkMaquina
        self.fkComponente = fkComponente
        self.limiteAlerta = limiteAlerta

    def gerarDado(self, mysql):
        query = ( "SELECT * FROM componentes "
        "WHERE idComponente = {}".format(self.fkComponente) )
        print("\ngerarDado")

        componente = mysql.select(query)[0]
        crawler = CrawlerOpenHardwareMonitor()

        return Dado(1,'null', crawler.getValor(componente[1]), self.idConfiguracao)
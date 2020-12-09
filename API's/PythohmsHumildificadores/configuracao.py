import datetime
from conexaobd import Mysql
from pythohms import CrawlerOpenHardwareMonitor
from leituras import Leitura

class Configuracao:
    def __init__(self, idConfiguracao, fkMaquina, fkComponente, limiteAlerta):
        self.idConfiguracao = idConfiguracao
        self.fkMaquina = fkMaquina
        self.fkComponente = fkComponente
        self.limiteAlerta = limiteAlerta

    def gerarDado(self, mysql):
        query = ( "SELECT * FROM componentes "
        "WHERE idComponente = {}".format(self.fkComponente) )
        print("\ngerarDado em configuracao.py",query)

        componente = mysql.select(query)[0]
        crawler = CrawlerOpenHardwareMonitor()

        return Leitura(1,'null', crawler.getValor(componente[1]), self.idConfiguracao)
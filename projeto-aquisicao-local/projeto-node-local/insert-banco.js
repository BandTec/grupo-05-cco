/*
 abrir a pasta deste arquivo via git bash e executar:
 npm i
 npm start
 talvez mostre uma mensagem de erro de placa arduino 
 mas depois vai começar a registrar os dados
*/

// se usar 'true' aqui, os dados serão gerados aleatórios e não recebidos da placa arduíno
const gerar_dados_aleatorios = true;
const intervalo_geracao_aleatoria_segundos = 5; // intervalo, em segundos, no qual os dados aleatórios serão gerados

// leitura dos dados do Arduino
const porta_serial = require('serialport');
const leitura_recebida = porta_serial.parsers.Readline;
const banco = require(`./banco`);

// prevenir problemas com muitos recebimentos de dados do Arduino
require('events').EventEmitter.defaultMaxListeners = 15;



const registros_mantidos_tabela_leitura = 7;


function iniciar_escuta() {

    porta_serial.list().then(entradas_seriais => {

        // este bloco trata a verificação de Arduino conectado (inicio)

        const entradas_seriais_arduino = entradas_seriais.filter(entrada_serial => {
            return entrada_serial.vendorId == 2341 && entrada_serial.productId == 43;
        });

        if (entradas_seriais_arduino.length != 1) {
            throw new Error("Nenhum Arduino está conectado ou porta USB sem comunicação ou mais de um Arduino conectado");
        }

        console.log("Arduino conectado na COM %s", entradas_seriais_arduino[0].comName);

        return entradas_seriais_arduino[0].comName;


        // este bloco trata a verificação de Arduino conectado (fim)

    }).then(arduinoCom => {

        // este bloco trata o recebimento dos dados do Arduino (inicio)

        // o baudRate deve ser igual ao valor em
        // Serial.begin(xxx) do Arduino (ex: 9600 ou 115200)
        const arduino = new porta_serial(arduinoCom, {
            baudRate: 9600
        });

        const parser = new leitura_recebida();
        arduino.pipe(parser);

        console.error('Iniciando escuta do Arduino');

        // Tudo dentro desse parser.on(...
        // é invocado toda vez que chegarem dados novos do Arduino
        parser.on('data', (dados) => {
            console.error(`Recebeu novos dados do Arduino: ${dados}`);
            try {
                // O Arduino deve enviar a temperatura e umidade de uma vez,
                // separadas por ":" (temperatura : umidade)
                const leitura = dados.split(':');
                registrar_leitura(Number(leitura[0]), Number(leitura[1]));
            } catch (e) {
                throw new Error(`Erro ao tratar os dados recebidos do Arduino: ${e}`);
            }

            // este bloco trata o recebimento dos dados do Arduino (fim)
        });

    }).catch(error => console.error(`Erro ao receber dados do Arduino ${error}`));
}

// função que recebe valores de temperatura e umidade
// e faz um insert no banco de dados
function registrar_leitura(temperatura, umidade) {

    console.log('Iniciando inclusão de novo registro...');
    console.log(`temperatura: ${temperatura}`);
    console.log(`umidade: ${umidade}`);

    banco.conectar().then(() => {

        return banco.sql.query(`
        INSERT into eventos (temperatura, umidade, momento)
        values (${temperatura}, ${umidade}, CONVERT(Datetime, '${agora()}', 120));
        
        delete from eventos where idEvento not in 
        (select top ${registros_mantidos_tabela_leitura} idEvento from eventos order by idEvento desc);`);

    }).catch(erro => {

        console.error(`Erro ao tentar registrar aquisição na base: ${erro}`);

    }).finally(() => {
        console.log('Registro inserido com sucesso! \n');
        banco.sql.close();
    });

}

// função que retorna data e hora atual no formato aaaa-mm-dd HH:mm:ss
function agora() {
    const momento_atual = new Date();
    const retorno = `${momento_atual.toLocaleDateString()} ${momento_atual.toLocaleTimeString()}`;
    console.log(`Data e hora atuais: ${retorno}`);
    return retorno;
}

let efetuando_insert = false;


if (gerar_dados_aleatorios) {
    // dados aleatórios
    setInterval(function() {
        console.log('Gerando valores aleatórios!');
        registrar_leitura(Math.min(Math.random() * 100, 60), Math.min(Math.random() * 200, 100))
    }, intervalo_geracao_aleatoria_segundos * 1000);
} else {
    // iniciando a "escuta" de dispositivos Arduino.
    console.log('Iniciando obtenção de valores do Arduino!');
    iniciar_escuta();
}

/*
 abrir a pasta deste arquivo via git bash e executar:
 npm i
 npm start
 talvez mostre uma mensagem de erro de placa arduino 
 mas depois vai começar a registrar os dados
*/
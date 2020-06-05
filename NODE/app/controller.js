const express = require('express');
const { ArduinoData } = require('./newserial')
const router = express.Router();


router.post('/sendData', (request, response) => {
    temperature = ArduinoDataTemp.List[ArduinoDataTemp.List.length -1];
    umidade = ArduinoDataHumidity.List[ArduinoDataHumidity.List.length -1];
    //luminosidade = ArduinoDataLuminosity.List[ArduinoDataLuminosity.List.length -1]

    var sql = `INSERT INTO medidas (nome, leituratemp, nomeumi, leituraumi) VALUES ('temperatura', ${temperature}, 'umidade', ${umidade})`;

    db.query(sql, function(err, result) {
        if (err) throw err;
        console.log("Number of records inserted: " + result.affectedRows);
      });
    

    response.sendStatus(200);
})

});

module.exports = router;
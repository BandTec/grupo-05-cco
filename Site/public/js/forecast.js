function produto(x, y) {
    var ret = [];
    for ( var i = 0 ; i < x.length ; i++ )
        ret.push(x[i] * y[i]);
    return ret;
}

function quadrados(x) {
    var ret = [];
    for ( var i = 0 ; i < x.length ; i++ )
        ret.push(x[i] * x[i]);
    return ret;
}

function somatorio(x) {
    var ret = 0;
    for ( var i = 0 ; i < x.length ; i++ )
        ret += x[i];
    return ret;
}

function media(x) {
    return somatorio(x) / x.length;
}

function calculoForecast(dados) {
    var x = [];
    for (let cont = 1; cont <= dados.length; cont++) {
        x.push(cont);
    }
    var y = dados;

    var m = somatorio(produto(x,y)) - somatorio(x) * somatorio(y) / x.length;
    m /= somatorio(quadrados(x)) - somatorio(x)*somatorio(x) / x.length;

    var b = media(y) - m * media(x);

    for (let ex = 1; ex <= 2; ex++) {
        var calculo = parseFloat((m * (y.length + 1) + b).toFixed(2));
        
        y.push(calculo);
        valores.push(calculo);

        var mesForecast = monthsTime[monthsTime.length - 1] + 1;
        if (mesForecast > 12) {
            mesForecast = 1;
        }
        monthsTime.push(mesForecast)
    }

}
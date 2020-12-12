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

function calculoForecast() {
    var x = [];
    for (let cont = 1; cont <= valores.length; cont++) {
        console.log(cont);
        x.push(cont);
    }
    var y = valores;
    console.log("x: ",x)
    console.log("y: ",valores)

    var m = somatorio(produto(x,y)) - somatorio(x) * somatorio(y) / x.length;
    m /= somatorio(quadrados(x)) - somatorio(x)*somatorio(x) / x.length;

    var b = media(y) - m * media(x);

    console.log(b);
    console.log(m);

    for (let ex = 1; ex <= 2; ex++) {
        var calculo = Math.round((m * (y.length + 1) + b), 0);
        console.log(`m * ${(y.length + 1)} + b = ${calculo}`)
        console.log("ex = ",ex)
        y.push(calculo);
    }

    console.log("y: ",y)
}
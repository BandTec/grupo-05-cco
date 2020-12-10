var x = [1,2,3,4,5,6,7,8,9,10];
var y = VALORES;
console.log("x: ",x)
console.log("y: ",y)

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

console.log(y)
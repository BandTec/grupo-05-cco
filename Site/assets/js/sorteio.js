 function sorteio() {

     var clima = (Math.random() * 40 + 1).toFixed(0);
     var umidade = (Math.random() * 99 + 1).toFixed(0);

     temperatura.innerHTML = `${clima}°`;
     umidades.innerHTML = `${umidade}%`;

     if (clima <= 32 && clima >= 20 && umidade >= 30 && umidade < 85) {
         imagemClima.src = "assets/images/weather.png";
         resultado.innerHTML = 'É um bom dia para caminhar!';
         imagemClima.style.backgroundColor = '#00F5A0';
         boxParque.style.color = '#00F5A0';
     } else {
         imagemClima.src = "assets/images/rain.png";
         resultado.innerHTML = 'Fique em casa hoje ou procure outro local!';
         imagemClima.style.backgroundColor = '#F15959';
         boxParque.style.color = '#F15959';
     }
 }
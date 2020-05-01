 function umidadeTelaSlide() {
     selectUmi.value = umidade.value;
 }

 function umidadeTelaInput() {
     umidade.value = selectUmi.value;
 }

 function temperaturaTelaSlide() {
     selectTemp.value = temperatura.value;
 }

 function umidadeTelaInput() {
     temperatura.value = selectTemp.value;
 }

 function sorteio() {

     var clima = (Math.random() * 40 + 1).toFixed(0);
     var umidade = (Math.random() * 99 + 1).toFixed(0);

     temperaturas.innerHTML = `${clima}°`;
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
     /*if (clima < 20 && umidade < 40 || clima > 28 || umidade > 70) {
         alert('Cuidado, o clima nao e a umidade nao esta propenso a atividades fisicas!!')
     }*/
 }
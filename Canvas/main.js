// setup canvas

const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

const width = (canvas.width = window.innerWidth); //window pai document (navegador) -> largura canvas vai ser a msm que a do navegador
const height = (canvas.height = window.innerHeight); //altura canvas vai ser a msm que a do navegador

// function to generate random number
//Math.random retorna num aleatorio entre 0 e 1
//mas quero entre 0 e 255 -> faz porcentagem
function random(min, max) {
  const num = Math.floor(Math.random() * (max - min + 1)) + min;
  return num;
}

// function to generate random color

function randomRGB() {
  return `rgb(${random(0, 255)},${random(0, 255)},${random(0, 255)})`;
}

//funcao construtora
function Shape(x, y, velx, vely) {
  this.x = x;
  this.y = y;
  this.velx = velx;
  this.vely = vely;
  this.exists=true;
  
}

function Ball(x,y,velx,vely,color,size){
  Shape.call(this,x,y,velx,vely);
  this.color = color;
  this.size = size;
}

//criando um objeto tipo ball
//let ball1= new Ball(0, 0, 10, 10, 'blue', 30);

//metodo desenha(draw)
Ball.prototype.draw = function () {
  //contexto comece o caminho
  ctx.beginPath();
  //cor do  contexto
  ctx.fillStyle = this.color;
  //desenhando o circulo (0->2PI)
  ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
  //preenche o desenho
  ctx.fill();
}

//desenhando bola na tela
//let green = new Ball(400, 200, 0, 0, 'rgb(0, 255, 0)', 50);
//green.draw();
//let red = new Ball(300, 100, 0, 0, 'rgb(255, 0, 0)', 50);
//red.draw();
//let blue = new Ball(200, 50, 0, 0, 'rgb(0, 0, 255)', 50);
//blue.draw();

//metodo atualiza posiçao bolinha
Ball.prototype.update = function () {

  //posiçoes fora da tela:
  //(0,0) -> y<0
  //(0,1) -> x>width
  //(1,1) -> height
  //(1,0) -> x<0

  //se posição + tamanho da bolinha saiu da tela (> largura)
  if((this.x+this.size)>=width){
    //troca sinal vel
    this.velx *= -1;
  }

  if((this.x+this.size)<=0){
    this.velx *= -1;
  }

  if((this.y+this.size)>=height){
    this.vely *= -1;
  }

  if((this.x+this.size)<=0){
    this.vely *= -1;
  }

  //So=S+vt
  this.x=this.x+this.velx*1;
  this.y=this.y+this.vely*1;


}

Ball.prototype.collisionDetected = function(){
  balls.forEach((ball,j) =>{
    if(!(this == ball)){
      const dx = this.x-ball.x;
      const dy = this.y-ball.y;
      //normalização: garamte que nao vai ter valor negativo
      const distance = Math.sqrt(dx*dx+dy*dy);

      if(distance<this.size+ball.size){
        this.color=ball.color=randomRGB();
        this.velx*=-1;
        this.vely*=-1;
        ball.velx*=-1;
        ball.vely*=-1;
      }
    }
  })
}

let balls=[];

while(balls.length<25)
{
  let size = random(10, 20);
  let ball = new Ball(
    random(0+size,width-size), 
    random(0+size,height-size),
    random(-7,7),
    random(-7,7), 
    randomRGB(),
    size
  );
  balls.push(ball);
}

//desenhando a bolinha na tela
//balls.forEach(ball => {
 // ball.draw(); });

 function loop(){
  ctx.fillStyle='rgba(0,0,0,1)';
  ctx.fillRect(0,0,width, height);

  balls.forEach(ball =>{
    ball.draw();
    ball.update();
    ball.collisionDetected();
  })

  requestAnimationFrame(loop);
 }

 loop();

 

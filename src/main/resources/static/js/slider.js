const cardsInner = document.querySelector('.cards_inner');

// Inicializar posición
let posX = 0;

// Drag funcionalidad con GSAP Draggable
Draggable.create(cardsInner, {
  type: "x",
  inertia: true,
  onDrag: function() {
    posX = this.x;
    rotateCards();
  },
  onThrowUpdate: function() {
    posX = this.x;
    rotateCards();
  }
});

// Función para rotar las tarjetas en curva
function rotateCards() {
    const cards = document.querySelectorAll('.card');
    const separation = 50; // separacion en píxeles entre cards
  
    cards.forEach((card, index) => {
      const rotation = -(index - cards.length / 2) * 20 + posX / 30;
      const offsetX = (index - cards.length / 2) * separation;
  
      card.style.transform = `translateX(${offsetX}px) rotateY(${rotation}deg) translateZ(200px)`;
    });
  }
  
  
  

// Llamar inicialmente
rotateCards();

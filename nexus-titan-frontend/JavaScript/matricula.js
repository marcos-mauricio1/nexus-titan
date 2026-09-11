let currentStep = 1;

document.addEventListener('DOMContentLoaded', () => {
  if (window.lucide) {
    lucide.createIcons();
  }

  const cpfInput = document.getElementById('cpf');
  const telInput = document.getElementById('telefone');
  const cepInput = document.getElementById('cep');

  // Máscara CPF
  cpfInput.addEventListener('input', (e) => {
    let v = e.target.value.replace(/\D/g, "");
    v = v.replace(/(\d{3})(\d)/, "$1.$2");
    v = v.replace(/(\d{3})(\d)/, "$1.$2");
    v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    e.target.value = v;
  });

  // Máscara Telefone
  telInput.addEventListener('input', (e) => {
    let v = e.target.value.replace(/\D/g, "");
    v = v.replace(/^(\d{2})(\d)/g, "($1) $2");
    v = v.replace(/(\d{5})(\d)/, "$1-$2");
    e.target.value = v;
  });

  // Máscara CEP
  cepInput.addEventListener('input', (e) => {
    let v = e.target.value.replace(/\D/g, "");
    v = v.replace(/^(\d{5})(\d)/, "$1-$2");
    e.target.value = v;
  });
});

function nextStep(step) {
  if (validateStep(step)) {
    if (step === 3) {
      preencherResumo();
    }
    changeStep(step + 1);
  }
}

function prevStep(step) {
  changeStep(step - 1);
}

function changeStep(newStep) {
  document.getElementById(`step-${currentStep}`).classList.remove('active');
  currentStep = newStep;
  document.getElementById(`step-${currentStep}`).classList.add('active');
  updateProgress();
}

function updateProgress() {
  const fill = document.getElementById('progressFill');
  const percentage = ((currentStep - 1) / 3) * 100;
  fill.style.width = `${percentage}%`;

  for (let i = 1; i <= 4; i++) {
    const indicator = document.getElementById(`stepIndicator-${i}`);
    if (i <= currentStep) {
      indicator.classList.add('active');
    } else {
      indicator.classList.remove('active');
    }
  }
}

function validateStep(step) {
  const stepElement = document.getElementById(`step-${step}`);
  const inputs = stepElement.querySelectorAll('input[required], select[required]');
  let isValid = true;

  inputs.forEach(input => {
    const block = input.parentElement;
    if (!input.value.trim()) {
      block.classList.add('invalid');
      isValid = false;
    } else {
      block.classList.remove('invalid');
    }
  });

  return isValid;
}

function preencherResumo() {
  document.getElementById('res-nome').textContent = document.getElementById('nome').value;
  document.getElementById('res-cpf').textContent = document.getElementById('cpf').value;
  document.getElementById('res-email').textContent = document.getElementById('email').value;
  document.getElementById('res-telefone').textContent = document.getElementById('telefone').value;
  document.getElementById('res-cidade').textContent = `${document.getElementById('cidade').value} / ${document.getElementById('estado').value.toUpperCase()}`;

  const modSelect = document.getElementById('modalidade');
  document.getElementById('res-modalidade').textContent = modSelect.value;

  const planoSelect = document.getElementById('plano');
  document.getElementById('res-plano').textContent = planoSelect.value;
}

document.getElementById('formMatricula').addEventListener('submit', (e) => {
  e.preventDefault();
  
  document.getElementById('formMatricula').classList.add('hidden');
  document.querySelector('.progress-bar-wrapper').classList.add('hidden');
  document.getElementById('successCard').classList.remove('hidden');
  
  if (window.lucide) {
    lucide.createIcons();
  }
});

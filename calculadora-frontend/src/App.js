import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [a, setA] = useState('');
  const [b, setB] = useState('');
  const [operation, setOperation] = useState('add');
  const [result, setResult] = useState('');
  const [history, setHistory] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/history')
      .then(response => response.json())
      .then(data => setHistory(data));
  }, [result]);

  const handleCalculate = () => {
    fetch(`http://localhost:8080/api/calculate?operation=${operation}&a=${a}&b=${b}`)
      .then(response => response.text())
      .then(data => setResult(data));
  };

  return (
    <div className="App">
      <h1>Calculadora</h1>
      <input type="number" value={a} onChange={e => setA(e.target.value)} placeholder="Número A" />
      <input type="number" value={b} onChange={e => setB(e.target.value)} placeholder="Número B" />
      <select value={operation} onChange={e => setOperation(e.target.value)}>
        <option value="add">Sumar</option>
        <option value="subtract">Restar</option>
        <option value="multiply">Multiplicar</option>
        <option value="divide">Dividir</option>
      </select>
      <button onClick={handleCalculate}>Calcular</button>
      <div>
        <h2>Resultado</h2>
        <p>{result}</p>
      </div>
      <div>
        <h2>Historial de Operaciones</h2>
        <ul>
          {history.map((entry, index) => (
            <li key={index}>{entry}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;

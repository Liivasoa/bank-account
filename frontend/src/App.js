import logo from './logo.svg';
import './App.css';
import {useState, useEffect} from "react";

function App() {

  const [amount, setAmount] = useState(0);
  const handleChange = (e) => {
    setAmount(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/transfer/init", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          amount,
          srcId: "cb50b3c1-9f3d-4531-8337-5046c797f991",
          dstId: "cb50b3c1-9f3d-4531-8337-5046c797f991"
        }),
      });
      if (response.ok) {
        alert("Virement initié avec succès !");
      } else {
        alert("Erreur lors de l'initiation du virement.");
      }
    } catch (error) {
      console.error("Erreur :", error);
    }
  };

  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    const socket = new WebSocket("ws://localhost:8080/ws/notifications");

    socket.onmessage = (event) => {
      const newNotification = event.data;
      setNotifications((prev) => [...prev, newNotification]);
    };

    socket.onopen = () => console.log("WebSocket connection established.");
    socket.onclose = (e) => console.log("WebSocket connection closed.", e);
    socket.onerror = (error) => console.error("WebSocket error:", error);

    return () => socket.close();
  }, []);

  return (
    <div className="App">
      <form onSubmit={handleSubmit}>
        <input type="number" name="amount" onChange={handleChange} value={amount}></input>
        <button type="submit">Executer virement</button>
      </form>
      <ul>
        {notifications.map((notification, index) => (
          <li key={index}>{notification}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;

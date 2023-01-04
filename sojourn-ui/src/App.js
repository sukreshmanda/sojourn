import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import HomeComponent from './components/HomeComponent';
import DataComponent from './components/DataComponent';
import TopPanel from './components/TopPanel';
import AddData from './components/AddData';
import { createContext, useState } from 'react';

export const DataContext = createContext(null);

function App() {

  const [data, setData] = useState(null);

  return (
    <DataContext.Provider value={data}>
      <div className="App">
        <BrowserRouter>
          <TopPanel />
          <br/>
          <Routes>
            <Route path="/" element={<HomeComponent/>} />
            <Route path="/home" element={<HomeComponent/>} />
            <Route path="/data" element={<DataComponent/>} />
            <Route path="/add-data" element={<AddData/>} />
          </Routes>
        </BrowserRouter>
      </div>
    </DataContext.Provider>
  );
}

export default App;

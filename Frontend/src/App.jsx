import './App.css'
import {Provider} from "react-redux";
import { store } from './app/store';

import LandingPage from './components/HomePage/LandingPage'
function App() {
  return(
    <Provider store={store}>
        <LandingPage/>
    </Provider>
  )
}

export default App

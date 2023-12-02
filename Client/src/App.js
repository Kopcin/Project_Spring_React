import { Route, Routes } from "react-router-dom"
import Main from "./components/Main"

function App() {
  return (
    <Routes>
      <Route path="/" exact element={<Main />} />
    </Routes>
  )
}

export default App
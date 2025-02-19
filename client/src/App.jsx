import "bootstrap/dist/css/bootstrap.min.css";
import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements
} from "react-router-dom";
import RootLayout from "./components/layout/RootLayout";

function App() {
  const router = createBrowserRouter(createRoutesFromElements(
    <Route path="/" element={<RootLayout />} />
  ));

  return (
    <>
    <h2>It's A Smorgasborg</h2>
    </>
  )
}

export default App

import "bootstrap/dist/css/bootstrap.min.css";
import {
  Route,
  Router,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements
} from "react-router-dom";
import RootLayout from "./components/layout/RootLayout";

function App() {
  const router = createBrowserRouter(createRoutesFromElements(
    <Route path="/" element={<RootLayout />} />
  ));

  return <RouterProvider router={router}/>
}

export default App

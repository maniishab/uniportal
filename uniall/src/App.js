import React from 'react';
import SearchUniversity from './components/universitySearch';
import { Container } from 'react-bootstrap';

const App = () => {
  return (
    <Container className="App">
       <h1 className="my-4">Search University</h1>
      <SearchUniversity />
    </Container>
  );
};

export default App;
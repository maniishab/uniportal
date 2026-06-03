import React, { useState, useEffect , useRef} from 'react';
import { searchUniversity, getCountries } from '../services/universityService';
import Pagination from 'react-bootstrap/Pagination';

  // Utility function to convert http to https
  const convertToHttps = (url) => {
     if (url.startsWith('http:')) {
       return url.replace('http:', 'https:');
     }
     console.log(url);
     return url;
   };

const SearchUniversity = () => {
  const [element, setElement] = useState('');
  const [elementValue, setElementValue] = useState('');
  const [results, setResults] = useState([]);
  const [filteredResults, setFilteredResults] = useState([]);
  const [error, setError] = useState(null);
  const [validationError, setValidationError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(10);
  const [filterName, setFilterName] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [selectedUniversity, setSelectedUniversity] = useState(null);
  const [countries, setCountries] = useState([]);
  const [isIframeLoading, setIsIframeLoading] = useState(true);
  const [iframeError, setIframeError] = useState(null);
  const iframeRef = useRef(null);
  const timeoutRef = useRef(null);

  const handleIframeLoad = () => {
    setIsIframeLoading(false);
    setIframeError(null);
    if (timeoutRef.current) {
      clearTimeout(timeoutRef.current);
    }
  };

  useEffect(() => {
    if (selectedUniversity && selectedUniversity.webPages.length > 0) {
      setIsIframeLoading(true);
      setIframeError(null);
      
      timeoutRef.current = setTimeout(() => {
        if (isIframeLoading) {
          setIframeError("This website cannot be displayed in an iframe due to security restrictions.");
          setIsIframeLoading(false);
          if (iframeRef.current) {
            iframeRef.current.srcdoc = `
              <html>
                <body style="display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; font-family: Arial, sans-serif; background-color: #f0f0f0;">
                  <div style="text-align: center; padding: 20px; background-color: white; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
                    <h2 style="color: #d32f2f;">Error Loading Content</h2>
                    <p>This website cannot be displayed in an iframe due to security restrictions.</p>
                    <p>Please visit the website directly:</p>
                    <a href="${convertToHttps(selectedUniversity.webPages[0])}" target="_blank" rel="noopener noreferrer" style="color: #1976d2; text-decoration: none;">${selectedUniversity.webPages[0]}</a>
                  </div>
                </body>
              </html>
            `;
          }
        }
      }, 5000); // 5 seconds timeout

      return () => {
        if (timeoutRef.current) {
          clearTimeout(timeoutRef.current);
        }
      };
    }
  }, [selectedUniversity]);

  const handleIframeError = () => {
    setIsIframeLoading(true);
    setIframeError("This website cannot be displayed in an iframe due to security restrictions.");
  };

  useEffect(() => {
    if (element === 'Country') {
      fetchCountries();
    }
  }, [element]);

  const fetchCountries = async () => {
    try {
      const data = await getCountries();
      setCountries(data);
    } catch (error) {
      console.error('Error fetching countries:', error);
    }
  };


  const handleSearch = async (e) => {
    e.preventDefault();
    setError(null); // Reset error state
    setValidationError(null); // Reset validation error state

    // Validate form fields
    if (!element) {
      setValidationError('Please select an element.');
      return;
    }

    if (!elementValue) {
      setValidationError('Please enter a value for the selected element.');
      return;
    }

    try {
      const data = await searchUniversity(element, elementValue);
      setResults(Array.isArray(data) ? data : []);
      setCurrentPage(1);
      setFilterName('');
    } catch (err) {
      console.error('Search error:', err.response || err.message);
      setError('Failed to search universities. Please check your input and try again.');
    }
  };

  useEffect(() => {
    const filtered = results.filter(university =>
      university.name.toLowerCase().includes(filterName.toLowerCase())
    );
    setFilteredResults(filtered);
    setCurrentPage(1);
  }, [results, filterName]);
   // Get current items
   const indexOfLastItem = currentPage * itemsPerPage;
   const indexOfFirstItem = indexOfLastItem - itemsPerPage;
   const currentItems = results.slice(indexOfFirstItem, indexOfLastItem);

   // Change page
   const paginate = (pageNumber) => setCurrentPage(pageNumber);

   // Calculate total pages
   const totalPages = Math.ceil(filteredResults.length / itemsPerPage);

   // Generate pagination items
   const getPaginationItems = () => {
    const items = [];
    const maxPagesToShow = 10;
    let startPage, endPage;

    if (totalPages <= maxPagesToShow) {
      startPage = 1;
      endPage = totalPages;
    } else {
      const maxPagesBeforeCurrentPage = Math.floor(maxPagesToShow / 2);
      const maxPagesAfterCurrentPage = Math.ceil(maxPagesToShow / 2) - 1;

      if (currentPage <= maxPagesBeforeCurrentPage) {
        startPage = 1;
        endPage = maxPagesToShow;
      } else if (currentPage + maxPagesAfterCurrentPage >= totalPages) {
        startPage = totalPages - maxPagesToShow + 1;
        endPage = totalPages;
      } else {
        startPage = currentPage - maxPagesBeforeCurrentPage;
        endPage = currentPage + maxPagesAfterCurrentPage;
      }
    }
      // Add "First" page
      if (currentPage > 1) {
        items.push(
          <Pagination.First key="first" onClick={() => paginate(1)} />
        );
      }
  
      // Add "Previous" page
      if (currentPage > 1) {
        items.push(
          <Pagination.Prev key="prev" onClick={() => paginate(currentPage - 1)} />
        );
      }
        // Add page numbers
    for (let page = startPage; page <= endPage; page++) {
      items.push(
        <Pagination.Item
          key={page}
          active={page === currentPage}
          onClick={() => paginate(page)}
        >
          {page}
        </Pagination.Item>
      );
    }

    // Add "Next" page
    if (currentPage < totalPages) {
      items.push(
        <Pagination.Next key="next" onClick={() => paginate(currentPage + 1)} />
      );
    }

    // Add "Last" page
    if (currentPage < totalPages) {
      items.push(
        <Pagination.Last key="last" onClick={() => paginate(totalPages)} />
      );
    }

    return items;
  };

  const handleRowDoubleClick = (university) => {
    setSelectedUniversity(university);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedUniversity(null);
  };

  return (
    <div className="container mt-4">
      <div className="row">
        
      {validationError && <p className="text-danger mt-3" style={{ color: 'red' }}>{validationError}</p>}
      {error && <p className="text-danger mt-3" style={{ color: 'red' }}>{error}</p>}
      <form className="form-container" onSubmit={handleSearch} >
      <div className="mb-3">
      <label htmlFor="elementValue" className="form-label">Filter Universities by : </label>
        <select id="element" className="form-select" value={element} onChange={(e) => setElement(e.target.value)}>

          <option value="">Select Filter</option>
          <option value="Name">Name</option>
          <option value="Country">Country</option>
        </select>
        </div>
        <div className="mb-3">
             <label htmlFor="elementValue" className="form-label">Filter Value : </label>
             {element === 'Country' ? (
               <select
                 id="elementValue"
                 className="form-select"
                 value={elementValue}
                 onChange={(e) => setElementValue(e.target.value)}
               >
                 <option value="">Select Country</option>
                 {countries.map((country) => (
                   <option key={country.country} value={country.country}>
                     {country.country}
                   </option>
                 ))}
               </select>
             ) : (
               <input
                 id="elementValue"
                 type="text"
                 className="form-control"
                 value={elementValue}
                 onChange={(e) => setElementValue(e.target.value)}
                 placeholder="Element Value"
               />
             )}
           </div>
        <button type="submit" className="btn btn-primary">Search</button>
      </form>
      <h1>Universities</h1>
          <h1>
          </h1>
          <h7>* Double click on the item list to preview the website:</h7>
    <div className="col-md-6">
      {results.length > 0 && (
        <>
      <table className="table table-striped table-hover mt-4">
           <thead>
             <tr>
               <th>Name</th>
               <th>Country</th>
               <th>Web Pages</th>
             </tr>
           </thead>
           <tbody>
              {currentItems.map((university) => (
                <tr key={university.id} onDoubleClick={() => handleRowDoubleClick(university)}>
                  <td>{university.name}</td>
                  <td>{university.country}</td>
                  <td>
                       {university.webPages.length > 1 ? (
                         <ul>
                           {university.webPages.map((webPage, index) => (
                             <li key={index}>
                               <a href={convertToHttps(webPage)} target="_blank" rel="noopener noreferrer">
                                 {convertToHttps(webPage)}
                               </a>
                             </li>
                           ))}
                         </ul>
                       ) : (
                         <a href={convertToHttps(university.webPages[0])} target="_blank" rel="noopener noreferrer">
                           {convertToHttps(university.webPages[0])}
                         </a>
                       )}
                     </td>
                </tr>
              ))}
            </tbody>
          </table>
          
          <Pagination className="justify-content-center">
            {getPaginationItems()}
          </Pagination>
        </>
        
      )}
       </div>
             <div className="col-md-6">
             <label htmlFor="iframeEr" className="form-label" color='#000'>* Some websites will not load on the frame </label>

            {selectedUniversity && selectedUniversity.webPages.length > 0 && (
               <>
                 {isIframeLoading && <div id="loading-animation">Loading...</div>}
                  {iframeError ? (
                <div className="error-message">
                  <p>{iframeError}</p>
                  <p>Please visit the website directly: <a href={convertToHttps(selectedUniversity.webPages[0])} target="_blank" rel="noopener noreferrer">{selectedUniversity.webPages[0]}</a></p>
                </div>
              ) : (
                 <iframe
                  ref={iframeRef}
                   src={convertToHttps(selectedUniversity.webPages[0])}
                   title="University Preview"
                   width="700px"
                   height="650px"
                   style={{ border: '2px solid #000'}}
                   onLoad={handleIframeLoad}
                //   onError={handleIframeError}
                 ></iframe>
                  )}
               </>
           )}     
       </div>
       <footer className="text-center mt-4">
           <p>&copy; {new Date().getFullYear()} Roberto Dure - Universities Search</p>
         </footer>
    </div>
    </div>
  );
};

export default SearchUniversity;
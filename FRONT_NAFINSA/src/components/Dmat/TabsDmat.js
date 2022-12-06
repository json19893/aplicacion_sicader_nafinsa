import React from 'react';
import { Container, Row, Tabs, Tab } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import ContentDmat1 from './ContentDmat1';
import ContentDmat2 from './ContentDmat2';
import ContentDmat3 from './ContentDmat3';
import ContentDmat4 from './ContentDmat4';


const TabsDmat = () => {
    return (
        <Container className="py-4">
            <Row className="justify-content-center">
                <Tabs justify variant="pills" defaultActiveKey="tab-1" className="mb-1 p-0">
                    <Tab eventKey="tab-1" title="Valuación Complemento BANXICO">
                        <ContentDmat1 />
                    </Tab>
                    <Tab eventKey="tab-2" title="Carga de archivos mensuales">
                        <ContentDmat2 />
                    </Tab>
                    <Tab eventKey="tab-3" title="Intereses generados al Margen">
                        <ContentDmat3 />
                    </Tab>
                    <Tab eventKey="tab-4" title="C10 antes B5">
                        <ContentDmat4 />
                    </Tab>
                </Tabs>
            </Row>

        </Container>
    );
};

export default TabsDmat;
import React from 'react';
import { Container, Row, Tabs, Tab } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import ContentConcilia1 from './ContentConcilia1';
import ContentConcilia2 from './ContentConcilia2';



const TabsConciliaConta = () => {
    return (
        <Container className="py-4">
            <Row className="justify-content-center">
                <Tabs justify variant="pills" defaultActiveKey="tab-1" className="mb-1 p-0">
                    <Tab eventKey="tab-1" title="Ejecución Conciliación Contable">
                        <ContentConcilia1 />
                    </Tab>
                    <Tab eventKey="tab-2" title="Estatus para Conciliación Diaria/Mensual">
                        <ContentConcilia2 />
                    </Tab>
                </Tabs>
            </Row>

        </Container>
    );
};

export default TabsConciliaConta;
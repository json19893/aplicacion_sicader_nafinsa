import { useState, useEffect } from 'react';
import { Container, Row,Button, Tabs,Card, Tab } from 'react-bootstrap';
import { QuestionOutlined } from '@ant-design/icons';
import 'bootstrap/dist/css/bootstrap.min.css';
import ContentSideca1 from '../contentSideca/contentSideca1';
import ContentSideca2 from '../contentSideca/contentSideca2';

const TabsSideca = () => {
   
    return (
        <Container className="py-4">
            <Row className="justify-content-center">
                <Tabs justify variant="pills" defaultActiveKey="tab-1" className="mb-1 p-0">
                    <Tab  eventKey="tab-1" title="Carga de Información de Cartas de Confirmación" >
                        <ContentSideca1 />
                    </Tab>
                    <Tab eventKey="tab-2" title="Carga de Estados de Cuenta ASIGNA">
                        <ContentSideca2 />
                    </Tab>

                </Tabs>
            </Row>

        </Container>
    );
};

export default TabsSideca;
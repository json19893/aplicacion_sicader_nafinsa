import { useState, useEffect } from 'react';
import { Table, Card,Popover,Button } from 'antd';
import { FileExcelOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { getCuentasConciliarReq10 } from '../../services/catalogosService'
const { Meta } = Card;
function CuentasConciliar() {

    const [cuentas, setCuentas] = useState([]);

    const columns = [
        {
            title: "Cuenta Contable",
            dataIndex: "cuenta",
            key: "cuenta",
            align: "center"
        },
        {
            title: "Ente",
            dataIndex: "ente",
            key: "ente",
            align: "center"
        },
        {
            title: "Moneda",
            dataIndex: "moneda",
            key: "moneda",
            align: "center"
        },
        {
            title: "Tipo de Ente",
            dataIndex: "tipoEnte",
            key: "tipoEnte",
            align: "center"
        },
        {
            title: "Nombre de cuenta",
            dataIndex: "cueNombre",
            key: "cueNombre",
            align: "center"
        }        
    ];

    useEffect(() => {
        async function loadCuentasConciliarReq10() {
            const response = await getCuentasConciliarReq10()

            if (response.status === 200) {
                setCuentas(response.data)
            }
        }
        loadCuentasConciliarReq10()
    }, []);

    const data = cuentas;
    const [open, setOpen] = useState(false);
    const handleOpenChange = (newOpen) => {
        setOpen(newOpen);
      };
    const desc=(
      <Card
      style={{
        width: 300,
        marginTop: 16,
      }}
     
    >
      <Meta
        description="Pantalla donde es posible visualizar las Cuentas Contables que se van a conciliar."
      />
    </Card>
    );
      const content = (
       <><CSVLink data={data} filename={"cuentasConciliar.csv"}>
                  <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
              </CSVLink>
              &nbsp;&nbsp;&nbsp;
              <Popover
              content={desc}
              trigger="click"
              placement="leftTop"
              open={open}
              onOpenChange={handleOpenChange}
          >
              <Button type="ghost" shape="circle" icon={<QuestionOutlined />} size="small"></Button>
          </Popover></>
      );
    return (
        <div>

            <Card size="small" align="left" title="Cuentas a Conciliar"
                headStyle={{ backgroundColor: '#39c0c4' }}
                 extra={content}
                   
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>

        </div>
    );
}

export default CuentasConciliar;
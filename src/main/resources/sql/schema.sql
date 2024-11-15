CREATE SEQUENCE p_sequence;

CREATE TABLE product
(
    id         				VARCHAR(100) DEFAULT 'p' || nextval('p_sequence'),
    status     				INT 		 NOT NULL,
    fulfillment_center_id   VARCHAR(100) NOT NULL,
    qty       				INT  NOT NULL,
    value  				    INT  NOT NULL,
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS widget;
  
CREATE TABLE widget (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  xAxis INT NOT NULL,
  yAxis INT NOT NULL,
  zIndex INT NOT NULL,
  width INT NOT NULL,
  height INT NOT NULL,
  createdDate DATE,
  lastModifiedDate DATE
);
 
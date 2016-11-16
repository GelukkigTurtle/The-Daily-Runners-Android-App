<?php
    
    include 'geoiploc.php';
    
    class LocationManager{
        
        /**
         * Obtiene el pais utilizando como parametros la latitud y longitud, 
         * esto utilizando la api disponible de google map.
         * @return Object
        */
        public function getCountryByLocation($latitude, $longitude){
            
            $latlng = $latitude.','.$longitude;
            $url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='.$latlng.'&sensor=false';
            
            $data = file_get_contents($url);
            $objectJSON = json_decode($data);
            
            
            foreach($objectJSON->results[0]->address_components as $object){
        
                if(in_array('country', $object->types)){
            
                   //echo $object->long_name.'('.$object->short_name.')';
                    return $object;
            
                }
            }
            
        }
        
        public function getCountryByIp($ip){
            
            $country = array('code' => getCountryFromIP($ip, 'code'), 
                            'abbr' => getCountryFromIP($ip, 'abbr'),
                            'name' => getCountryFromIP($ip, 'name'));
                            
            return $country;  
            
        }
        
    }


?>
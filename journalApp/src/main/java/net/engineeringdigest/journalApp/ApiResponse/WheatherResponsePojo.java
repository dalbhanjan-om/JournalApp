package net.engineeringdigest.journalApp.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WheatherResponsePojo {
        public Current current;
        @Getter
        @Setter
    public class Current{

        public double temp_c;

        public double feelslike_c;

    }




}

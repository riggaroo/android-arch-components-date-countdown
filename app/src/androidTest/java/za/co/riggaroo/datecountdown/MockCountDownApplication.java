package za.co.riggaroo.datecountdown;

import za.co.riggaroo.datecountdown.dao.DaggerEventDaoTest_MockCountdownComponent;
import za.co.riggaroo.datecountdown.injection.CountdownComponent;
import za.co.riggaroo.datecountdown.injection.MockCountdownModule;

/**
 * @author rebeccafranks
 * @since 2017/05/12.
 */

public class MockCountDownApplication extends CountdownApplication {

    @Override
    public CountdownComponent createCountdownComponent() {
        return DaggerEventDaoTest_MockCountdownComponent.builder()
                .mockCountdownModule(new MockCountdownModule(this)).build();
    }
}

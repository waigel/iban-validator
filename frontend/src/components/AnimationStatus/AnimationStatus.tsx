import React, {useEffect} from 'react';
import lottie from 'lottie-web';

import lottie_error_status from '../../lottie/18053-no-error-cancelled.json';
import lottie_success_status from '../../lottie/114089-add-to-cart-checkmark-animation.json';

export interface AnimationStatusProps {
  status: 'success' | 'failed';
}
export const AnimationStatus = ({status}: AnimationStatusProps) => {
  const lottieId =
    status === 'success' ? 'lottie_success_status' : 'lottie_error_status';
  useEffect(() => {
    lottie.destroy(lottieId);
    lottie.loadAnimation({
      container: document.querySelector(`#${lottieId}`) as Element,
      animationData:
        status === 'success' ? lottie_success_status : lottie_error_status,
      name: lottieId,
      loop: false,
    });
  }, []);

  return <div id={lottieId} />;
};
